import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

class SteamTest1 extends App{
  implicit val system = ActorSystem("Test1") // Akka actor system
  implicit val materializer = ActorMaterializer // evaluation stream context
  import system.dispatcher
}
