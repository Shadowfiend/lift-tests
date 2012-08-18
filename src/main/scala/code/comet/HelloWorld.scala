package code { package comet {
  import net.liftweb._
    import comet._
    import common._
    import http._
      import js._
        import JsCmds._
        import JE._
      import SHtml._
    import util.Helpers._

  class CallableFunction(name:String, callback:(String)=>JsCmd, args:List[String] = List()) extends JsCmd {
    override val toJsCmd =
      Function(
        name, args,
        ajaxCall(JsRaw("Array.prototype.slice.call(arguments).join('|')"), callback)._2
      ).toJsCmd
  }

  case class ShortCometAjax(callback:(String)=>JsCmd) extends CallableFunction("shortCometAjax", callback)
  case class LongCometAjax(callback:(String)=>JsCmd) extends CallableFunction("longCometAjax", callback)

  case class TellMe(thing:String)

  class HelloWorldComet extends CometActor {
    def render = {
      ShortCometAjax(ajaxHandler(0, "SHORT")) &
      LongCometAjax(ajaxHandler(9000, "LONG"))
    }

    override def lowPriority = {
      case TellMe(thing) =>
        partialUpdate(Alert(thing))
    }

    def ajaxHandler(wait:Int, answer:String) : (String)=>JsCmd = {
      (s:String) => {
        Thread.sleep(wait)

        this ! TellMe(answer)

        Alert(answer + " from AJAX")
      }
    }
  }
} }
