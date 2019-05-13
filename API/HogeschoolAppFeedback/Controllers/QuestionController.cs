using System.Linq;
using System.Collections.Generic;
using HogeschoolAppFeedback.Models;
using Microsoft.AspNetCore.Mvc;

namespace HogeschoolAppFeedback.Controllers {
    [Route( "api/[controller]" )]
    [ApiController]
    public class QuestionController : ControllerBase {
        private HogeschoolFeedbackContext context;

        public QuestionController( HogeschoolFeedbackContext context ) {
            this.context = context;
        }

        [HttpGet]
        public ActionResult<IEnumerable<Question>> Get() {
            IEnumerable<Question> questions = this.context.Questions.ToArray();

            if ( !questions.Any() ) {
                return NotFound();
            }

            return Ok( questions );
        }

        [HttpGet( "{id}" )]
        public ActionResult<Question> Get( int id ) {
            Question Questions = this.context.Questions
                .Where( question => question.QuestionId == id)
                .FirstOrDefault();

            if ( Questions == null ) {
                return NotFound();
            }

            return Ok( Questions );
        }

        [HttpPost]
        public ActionResult Post( [FromBody] Question value ) {
            if ( !ModelState.IsValid || this.context.Questions.Contains( value ) ) {
                return BadRequest();
            }

            this.context.Questions.Add( value );
            this.context.SaveChanges();

            return Ok();
        }

        [HttpPut( "{id}" )]
        public IActionResult Close( int id ) {
            Question Questions = this.context.Questions
               .Where( question => question.QuestionId == id )
               .FirstOrDefault();

            if ( Questions == null ) {
                return NotFound();
            }

            Questions.EndDate = System.DateTime.Now;
            this.context.Questions.Update( Questions );
            this.context.SaveChanges();

            return Ok();
        }
    }
}
