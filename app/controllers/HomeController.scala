package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import AStarPathFinding.Graph


/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
 
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
  
  
  val g=new Graph("./conf/words_alpha.txt");
  
  
  
  def findPath(name1:String, name2:String)=Action{
      g.findPath(name1,name2)
     val result=g.getPath(name1,name2)
     
     
     Ok(result)
  
  }
  
  
  def test()=Action{
  
  Ok(views.html.test())
  
  }
  
  
  
  
}
