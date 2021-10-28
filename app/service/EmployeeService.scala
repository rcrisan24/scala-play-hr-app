package service

import models.Employee

trait EmployeeService {
  def findAll() = {
    Employee.createAll()
  }
}

object EmployeeService extends EmployeeService {

}
