package models

import play.api.libs.json.Json

case class Employee(id: Int, name : String, email : String, role : String)


object Employee {
  def createAll() : List[Employee] = List(
    Employee(123, "Mathew", "mathew@gmail.com", "Engineer"),
    Employee(124, "Mark", "mark@gmail.com", "Senior Engineer"),
    Employee(125, "Jacob", "jacob@gmail.com", "Junior Engineer"),
    Employee(126, "Andrew", "andrew@gmail.com", "Architect"),
  )

  implicit val format = Json.format[Employee]
}
