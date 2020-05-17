package playground

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}
import scala.concurrent.ExecutionContext.global
import scala.concurrent.Future

object Concourse extends App {

  implicit val system = ActorSystem("Concourse")
  implicit val materializer = ActorMaterializer()
  implicit val ex = global

  case class Blueprint(location: String)

  case class EnvInfo(httpProxy: String, httpPort: Int)

  def callShepForLock(b: Blueprint): Future[EnvInfo] = {
    Future {
      Thread.sleep(2000L)
      EnvInfo("10.12.30.2", 80)
    }
  }

  val input = Source.single(Blueprint("1"))
  val claimShepherdLock = Flow[Blueprint].mapAsync(2)(callShepForLock)

  val g = input.via(claimShepherdLock).to(Sink.foreach(println))
  g.run
}
