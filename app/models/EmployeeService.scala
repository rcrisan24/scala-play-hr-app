package models

trait EmployeeService {
  def findAll() = {
    Employee.createAll()
  }
}

object EmployeeService extends EmployeeService {

}
