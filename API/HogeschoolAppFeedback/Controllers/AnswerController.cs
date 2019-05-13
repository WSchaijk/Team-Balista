using System.Linq;
using System.Collections.Generic;
using HogeschoolAppFeedback.Models;
using Microsoft.AspNetCore.Mvc;

namespace HogeschoolAppFeedback.Controllers {
    [Route( "api/[controller]" )]
    [ApiController]
    public class AnswerController : ControllerBase {
        private HogeschoolFeedbackContext context;

        public AnswerController( HogeschoolFeedbackContext context ) {
            this.context = context;
        }

        [HttpGet( "{questionid}" )]
        public ActionResult<IEnumerable<Answer>> Get( int questionid ) {
            IEnumerable<Answer> Answers = this.context.Answers
                .Where( answer => answer.QuestionId == questionid )
                .ToArray();

            if ( !Answers.Any() ) {
                return NotFound();
            }

            return Ok( Answers );
        }

        // POST api/values
        [HttpPost]
        public ActionResult Post( [FromBody] Answer value ) {
            if ( !ModelState.IsValid || this.context.Answers.Contains( value ) ) {
                return BadRequest();
            }

            this.context.Answers.Add( value );
            this.context.SaveChanges();

            return Ok();
        }
    }
}
