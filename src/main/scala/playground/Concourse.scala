package playground

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}
import net.mikolak.travesty
import net.mikolak.travesty.OutputFormat
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

  val input = Source.single(Blueprint("1")).named("blueprint")
  val claimShepherdLock = Flow[Blueprint].mapAsync(2)(callShepForLock).named("shepherd")
  val sinkIgnore = Sink.ignore.named("end")

  val g = input.via(claimShepherdLock).to(sinkIgnore)

  //render as image to file, PNG is supported as well
  travesty.toFile(g, OutputFormat.SVG)("target/stream.svg")

  //render as text "image"
  system.log.info(travesty.toString(g)) //take care NOT to wrap the text


  g.run
}
