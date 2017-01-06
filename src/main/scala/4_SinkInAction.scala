import akka.actor.{ActorRef, ActorSystem}
import akka.stream.scaladsl.Source
import akka.stream.{ActorMaterializer, OverflowStrategy}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object SinkInAction extends App {

  implicit val system = ActorSystem("Test1")
  implicit val materializer = ActorMaterializer()

  def run(actor: ActorRef): Future[Unit] = {
    Future {
      Thread.sleep(300)
      actor ! 1
    }
    Future {
      Thread.sleep(200)
      actor ! 2
    }
    Future {
      Thread.sleep(100)
      actor ! 3
    }
  }

  val s = Source
    .actorRef[Int](bufferSize = 0, overflowStrategy = OverflowStrategy.fail)
    .mapMaterializedValue(run)

  s runForeach println
}
