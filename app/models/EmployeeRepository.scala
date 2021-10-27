package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


class EmployeeRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext){
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class EmployeeTable(tag: Tag) extends Table[Employee](tag, "employee") {

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def email = column[String]("email")
    def role = column[String]("role")

    override def * = (id, name, email, role) <> ((Employee.apply _).tupled, Employee.unapply)
  }

  private val employee = TableQuery[EmployeeTable]

  def insert(name: String, email: String, role: String): Future[Employee] = db.run {
    (employee.map(e => (e.name, e.email, e.role))
      returning employee.map(_.id)
      into ((nameEmailRole, id) => Employee(id, nameEmailRole._1, nameEmailRole._2, nameEmailRole._3))
      )+=(name, email, role)
  }

  def list(): Future[Seq[Employee]] = db.run {
    employee.result
  }
}
