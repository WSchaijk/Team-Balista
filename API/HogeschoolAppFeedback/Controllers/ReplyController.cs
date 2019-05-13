using System.Linq;
using System.Collections.Generic;
using HogeschoolAppFeedback.Models;
using Microsoft.AspNetCore.Mvc;

namespace HogeschoolAppFeedback.Controllers {
    [Route( "api/[controller]" )]
    [ApiController]
    public class ReplyController : ControllerBase {
        private HogeschoolFeedbackContext context;

        public ReplyController( HogeschoolFeedbackContext context ) {
            this.context = context;
        }

        [HttpGet( "{questionid}" )]
        public ActionResult<IEnumerable<Reply>> Get( int questionid ) {
            IEnumerable<Reply> Replies = this.context.Replies
                .Where( reply => reply.QuestionId == questionid )
                .ToArray();

            if ( !Replies.Any() ) {
                return NotFound();
            }

            return Ok( Replies );
        }

        [HttpPost]
        public ActionResult Post( [FromBody] Reply value ) {
            if ( !ModelState.IsValid || this.context.Replies.Contains( value ) ) {
                return BadRequest();
            }

            this.context.Replies.Add( value );
            this.context.SaveChanges();

            return Ok();
        }
    }
}
