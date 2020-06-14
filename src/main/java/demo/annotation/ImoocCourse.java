package demo.annotation;

@CourseInfoAnnotation(courseName = "数据结构",courseTag = "基础课", courseProfile = "课程简介")
public class ImoocCourse {

    @PersonInfoAnnotation(name = "Lhk" ,language = "Chinese")
    private String author;

    @CourseInfoAnnotation(courseName = "校园商铺",courseProfile = "不错的项目", courseTag = "项目",
    courseIndex = 110)
    public void getCourseInfo(){

    }
}
