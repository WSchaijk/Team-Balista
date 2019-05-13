using System.Collections.Generic;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using HogeschoolAppFeedback.Models;

namespace HogeschoolAppFeedback.Controllers {
    [Route( "api/[controller]" )]
    [ApiController]
    public class QuestionTypeController : ControllerBase {
        private HogeschoolFeedbackContext context;

        public QuestionTypeController( HogeschoolFeedbackContext context ) {
            this.context = context;
        }

        [HttpGet]
        public ActionResult<IEnumerable<QuestionType>> Get() {
            IEnumerable<QuestionType> questionTypes = this.context.QuestionTypes.ToArray();

            if ( !questionTypes.Any() ) {
                return NotFound();
            }

            return Ok( questionTypes );
        }

        [HttpGet( "{id}" )]
        public ActionResult<string> Get( int id ) {
            QuestionType QuestionType = this.context.QuestionTypes
                .Where( questionType => questionType.QuestionTypeId == id )
                .FirstOrDefault();

            if ( QuestionType == null ) {
                return NotFound();
            }

            return Ok( QuestionType );
        }
    }
}
