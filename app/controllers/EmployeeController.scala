package controllers

import models.EmployeeRepository
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, BaseController, ControllerComponents, MessagesAbstractController, MessagesControllerComponents, Request}

import javax.inject._
import scala.concurrent.ExecutionContext

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class EmployeeController @Inject()(employeeRepo: EmployeeRepository,
                                   cc: MessagesControllerComponents)
                                  (implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  def getEmployees(): Action[AnyContent] = Action.async { implicit request =>
    employeeRepo.list().map { employees =>
      Ok(Json.toJson(employees))
    }
  }

  def createEmployee() = Action { implicit request =>
    val body = request.body
    val jsonObject = body.asJson

    jsonObject match {
      case Some(json) => {
        val name = json("name").as[String]
        val email = json("email").as[String]
        val role = json("role").as[String]

        println(s"Decomposing json $name $email $role")
        employeeRepo.insert(name, email, role)
        Created(json.toString)
      }
      case None => BadRequest
    }
  }

  case class EmployeeDto(name: String, email: String, role: String)
}
