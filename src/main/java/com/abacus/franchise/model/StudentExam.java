package com.abacus.franchise.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.abacus.franchise.utility.ExamStatus;
import com.abacus.franchise.utility.ExamType;
import com.abacus.franchise.utility.ReattemptStatus;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;

@Entity
public class StudentExam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	@ManyToOne
	@JoinColumn(name = "attemptId", nullable = false)
	private ExamAttempt examAttempt;

	@Column(nullable = false)
	private ExamStatus Examstatus = ExamStatus.ASSIGNED; // Example values: "Assigned", "Completed", "Pending"

	private ExamType examType;

	private Boolean mark_as_download = false;

	private Long franchiseId;

	private String examStartTime;
	private String examSubmissionTime;

	@Column
	private Integer marks; // Stores the student's score for the exam

	@Column
	private String feedback; // Stores any feedback or comments about the exam

	@Column(nullable = false)
	private Timestamp assignedAt;

//	@Column(nullable = false)
//	private boolean examAssign; // It should be mapped to a column in the table

	@Column
	private Timestamp completedAt; // When the student completed the exam

	private String examTime;
	private String examName;
	private int question_count;
	private String course_name;
	private long course_id;

	@ElementCollection
	@CollectionTable(name = "exam_attempt_questions", joinColumns = @JoinColumn(name = "attempt_id"))
	@Column(name = "question_id")
	private List<Long> questionIds; // Store IDs of questions

	@ElementCollection
	@CollectionTable(name = "exam_attempt_answers", joinColumns = @JoinColumn(name = "attempt_id"))
	@MapKeyColumn(name = "question_id")
	@Column(name = "answer")
	private Map<Long, String> answers = new HashMap<>(); // Store correct answers by question ID

	@ElementCollection
	@CollectionTable(name = "student_selected_answers", joinColumns = @JoinColumn(name = "attempt_id"))
	@MapKeyColumn(name = "question_id")
	@Column(name = "answer")
	private Map<Long, String> studentAnswers = new HashMap<>();

	private ReattemptStatus reattemptStatus = ReattemptStatus.PENDING;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public ExamAttempt getExamAttempt() {
		return examAttempt;
	}

	public void setExamAttempt(ExamAttempt examAttempt) {
		this.examAttempt = examAttempt;
	}

	public ExamStatus getExamstatus() {
		return Examstatus;
	}

	public void setExamstatus(ExamStatus examstatus) {
		Examstatus = examstatus;
	}

	public ExamType getExamType() {
		return examType;
	}

	public void setExamType(ExamType examType) {
		this.examType = examType;
	}

	public Long getFranchiseId() {
		return franchiseId;
	}

	public void setFranchiseId(Long franchiseId) {
		this.franchiseId = franchiseId;
	}

	public Integer getMarks() {
		return marks;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Timestamp getAssignedAt() {
		return assignedAt;
	}

	public void setAssignedAt(Timestamp assignedAt) {
		this.assignedAt = assignedAt;
	}

	public Timestamp getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(Timestamp completedAt) {
		this.completedAt = completedAt;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public int getQuestion_count() {
		return question_count;
	}

	public void setQuestion_count(int question_count) {
		this.question_count = question_count;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public long getCourse_id() {
		return course_id;
	}

	public void setCourse_id(long course_id) {
		this.course_id = course_id;
	}

	public List<Long> getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(List<Long> questionIds) {
		this.questionIds = questionIds;
	}

	public Map<Long, String> getAnswers() {
		return answers;
	}

	public void setAnswers(Map<Long, String> answers) {
		this.answers = answers;
	}

	public Map<Long, String> getStudentAnswers() {
		return studentAnswers;
	}

	public void setStudentAnswers(Map<Long, String> studentAnswers) {
		this.studentAnswers = studentAnswers;
	}

	public ReattemptStatus getReattemptStatus() {
		return reattemptStatus;
	}

	public void setReattemptStatus(ReattemptStatus reattemptStatus) {
		this.reattemptStatus = reattemptStatus;
	}

	public Boolean getMark_as_download() {
		return mark_as_download;
	}

	public void setMark_as_download(Boolean mark_as_download) {
		this.mark_as_download = mark_as_download;
	}

	public String getExamStartTime() {
		return examStartTime;
	}

	public void setExamStartTime(String examStartTime) {
		this.examStartTime = examStartTime;
	}

	public String getExamSubmissionTime() {
		return examSubmissionTime;
	}

	public void setExamSubmissionTime(String examSubmissionTime) {
		this.examSubmissionTime = examSubmissionTime;
	}

	public StudentExam(Long id, Student student, ExamAttempt examAttempt, ExamStatus examstatus, ExamType examType,
			Boolean mark_as_download, Long franchiseId, String examStartTime, String examSubmissionTime, Integer marks,
			String feedback, Timestamp assignedAt, Timestamp completedAt, String examTime, String examName,
			int question_count, String course_name, long course_id, List<Long> questionIds, Map<Long, String> answers,
			Map<Long, String> studentAnswers, ReattemptStatus reattemptStatus) {
		super();
		this.id = id;
		this.student = student;
		this.examAttempt = examAttempt;
		Examstatus = examstatus;
		this.examType = examType;
		this.mark_as_download = mark_as_download;
		this.franchiseId = franchiseId;
		this.examStartTime = examStartTime;
		this.examSubmissionTime = examSubmissionTime;
		this.marks = marks;
		this.feedback = feedback;
		this.assignedAt = assignedAt;
		this.completedAt = completedAt;
		this.examTime = examTime;
		this.examName = examName;
		this.question_count = question_count;
		this.course_name = course_name;
		this.course_id = course_id;
		this.questionIds = questionIds;
		this.answers = answers;
		this.studentAnswers = studentAnswers;
		this.reattemptStatus = reattemptStatus;
	}

	public StudentExam() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "StudentExam [id=" + id + ", student=" + student + ", examAttempt=" + examAttempt + ", Examstatus="
				+ Examstatus + ", examType=" + examType + ", mark_as_download=" + mark_as_download + ", franchiseId="
				+ franchiseId + ", marks=" + marks + ", feedback=" + feedback + ", assignedAt=" + assignedAt
				+ ", completedAt=" + completedAt + ", examTime=" + examTime + ", examName=" + examName
				+ ", question_count=" + question_count + ", course_name=" + course_name + ", course_id=" + course_id
				+ ", questionIds=" + questionIds + ", answers=" + answers + ", studentAnswers=" + studentAnswers
				+ ", reattemptStatus=" + reattemptStatus + "]";
	}

}