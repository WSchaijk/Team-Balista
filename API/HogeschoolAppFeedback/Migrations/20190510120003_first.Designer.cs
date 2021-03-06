﻿// <auto-generated />
using System;
using HogeschoolAppFeedback.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

namespace HogeschoolAppFeedback.Migrations
{
    [DbContext(typeof(HogeschoolFeedbackContext))]
    [Migration("20190510120003_first")]
    partial class first
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn)
                .HasAnnotation("ProductVersion", "2.1.8-servicing-32085")
                .HasAnnotation("Relational:MaxIdentifierLength", 63);

            modelBuilder.Entity("HogeschoolAppFeedback.Models.Answer", b =>
                {
                    b.Property<int>("AnswerId")
                        .ValueGeneratedOnAdd();

                    b.Property<string>("AnswerText");

                    b.Property<int>("QuestionId");

                    b.HasKey("AnswerId");

                    b.HasIndex("QuestionId");

                    b.ToTable("Answers");
                });

            modelBuilder.Entity("HogeschoolAppFeedback.Models.Question", b =>
                {
                    b.Property<int>("QuestionId")
                        .ValueGeneratedOnAdd();

                    b.Property<string>("Asker");

                    b.Property<DateTime>("EndDate");

                    b.Property<string>("QuestionText");

                    b.Property<int>("QuestionTypeId");

                    b.HasKey("QuestionId");

                    b.HasIndex("QuestionTypeId");

                    b.ToTable("Questions");
                });

            modelBuilder.Entity("HogeschoolAppFeedback.Models.QuestionType", b =>
                {
                    b.Property<int>("QuestionTypeId")
                        .ValueGeneratedOnAdd();

                    b.Property<string>("QuestionTypeName");

                    b.HasKey("QuestionTypeId");

                    b.ToTable("QuestionTypes");
                });

            modelBuilder.Entity("HogeschoolAppFeedback.Models.Reply", b =>
                {
                    b.Property<int>("ReplyId")
                        .ValueGeneratedOnAdd();

                    b.Property<int>("QuestionId");

                    b.Property<string>("ReplyText");

                    b.HasKey("ReplyId");

                    b.HasIndex("QuestionId");

                    b.ToTable("Replies");
                });

            modelBuilder.Entity("HogeschoolAppFeedback.Models.Answer", b =>
                {
                    b.HasOne("HogeschoolAppFeedback.Models.Question", "Question")
                        .WithMany()
                        .HasForeignKey("QuestionId")
                        .OnDelete(DeleteBehavior.Cascade);
                });

            modelBuilder.Entity("HogeschoolAppFeedback.Models.Question", b =>
                {
                    b.HasOne("HogeschoolAppFeedback.Models.QuestionType", "QuestionType")
                        .WithMany()
                        .HasForeignKey("QuestionTypeId")
                        .OnDelete(DeleteBehavior.Cascade);
                });

            modelBuilder.Entity("HogeschoolAppFeedback.Models.Reply", b =>
                {
                    b.HasOne("HogeschoolAppFeedback.Models.Question", "Question")
                        .WithMany()
                        .HasForeignKey("QuestionId")
                        .OnDelete(DeleteBehavior.Cascade);
                });
#pragma warning restore 612, 618
        }
    }
}
