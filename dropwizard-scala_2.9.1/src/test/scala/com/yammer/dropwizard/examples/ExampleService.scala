package com.yammer.dropwizard.examples

import com.yammer.dropwizard.config.Environment
import com.yammer.dropwizard.ScalaService
import com.codahale.logula.Logging

object ExampleService extends ScalaService[ExampleConfiguration]("example") with Logging {
  addCommand(new SayCommand)
  addCommand(new SplodyCommand)

  def initialize(configuration: ExampleConfiguration, environment: Environment) {
    environment.addResource(new HelloWorldResource(configuration.saying))
    environment.addResource(new UploadResource)
    environment.addResource(new ProtectedResource)
    environment.addResource(new SplodyResource)
    environment.addHealthCheck(new DumbHealthCheck)
    environment.manage(new StartableObject(configuration.saying))
  }
}
