using System;
using Microsoft.EntityFrameworkCore;

namespace HogeschoolAppFeedback.Models {
    public class HogeschoolFeedbackContext : DbContext {

        public DbSet<Question> Questions { get; set; }
        public DbSet<QuestionType> QuestionTypes { get; set; }
        public DbSet<Reply> Replies { get; set; }
        public DbSet<Answer> Answers { get; set; }

        public HogeschoolFeedbackContext( DbContextOptions<HogeschoolFeedbackContext> options ) : base( options ) { }
    }

    public class Question {
        public int QuestionId { get; set; }
        public string QuestionText { get; set; }
        public string Asker { get; set; }
        public DateTime EndDate { get; set; }

        public int QuestionTypeId { get; set; }
        public QuestionType QuestionType { get; set; }
    }

    public class QuestionType {
        public int QuestionTypeId { get; set; }
        public string QuestionTypeName { get; set; }
    }

    public class Reply {
        public int ReplyId { get; set; }
        public string ReplyText { get; set; }

        public int QuestionId { get; set; }
        public Question Question { get; set; }
    }

    public class Answer {
        public int AnswerId { get; set; }
        public string AnswerText { get; set; }

        public int QuestionId { get; set; }
        public Question Question { get; set; }
    }
}
