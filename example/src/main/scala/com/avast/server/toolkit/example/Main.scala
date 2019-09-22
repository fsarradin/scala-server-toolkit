package com.avast.server.toolkit.example

import cats.effect.Resource
import com.avast.server.toolkit.example.config.Configuration
import com.avast.server.toolkit.pureconfig.PureConfigModule
import zio.interop.catz._
import zio.{Task, ZIO}

object Main extends CatsApp {

  def program: Resource[Task, Unit] = {
    for {
      configuration <- Resource.liftF(PureConfigModule.makeOrRaise[Task, Configuration])
    } yield ()
  }

  override def run(args: List[String]): ZIO[Environment, Nothing, Int] = {
    program
      .use(_ => Task.never)
      .fold(
        _ => 1,
        _ => 0
      )
  }

}