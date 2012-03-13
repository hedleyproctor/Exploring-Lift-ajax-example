package code.snippet

import _root_.net.liftweb.http.SHtml._
import _root_.net.liftweb.http.js.JE._
import _root_.net.liftweb.http.js.JsCmds._
import scala.xml.NodeSeq
import net.liftweb.util._
import Helpers._
import net.liftweb.http.js.JsCmd
import net.liftweb.http.SHtml

/** This example is based on the one in "Exploring Lift", called "A more complex AJAX example".
 * I've changed it slightly to use CSS selectors rather than the bind method.
 *
 * http://exploring.liftweb.net/master/index-11.html#toc-Section-11.3
 *
 */
class Ajax {

    def ajaxFunc1() : JsCmd = JsRaw("alert('Button1 clicked')")
    
  def ajaxFunc2(str: String) : JsCmd = {
    println("Received " + str)
    JsRaw("alert('Button2 clicked')")
  }
    
  def ajaxFunc3() : JsCmd = JsRaw("alert('Button3 clicked')")
  
// define a snippet method
	def render = {
		 "#button1" #> SHtml.ajaxButton("Press me", ajaxFunc1 _) &
            "#button2" #>
              // ajaxCall and ajaxInvoke actually returns a pair (String, JsExp).
              // The String is used for garbage collection, so we only need
              // to use the JsExp element (_2).
			  // this line will give an exception on compile
              <button onclick={SHtml.ajaxCall(Str("Button-2"), ajaxFunc2 _)._2}>Press me 2</button> &
            "#button3" #>
			// if I fix the above line by changing _2 to _2.toJsCmd, then this line gives the same exception
              <button onclick={SHtml.ajaxInvoke(ajaxFunc3 _)._2}>Press me 3</button> 
			  // if I fix this line with the same change, the code compiles, but there is no alert on the client
	}
}