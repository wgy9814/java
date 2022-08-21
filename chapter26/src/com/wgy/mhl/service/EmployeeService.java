package chapter26.src.com.wgy.mhl.service;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/21 15:27
 */


import chapter26.src.com.wgy.mhl.dao.EmployeeDAO;
import chapter26.src.com.wgy.mhl.domain.Employee;

/**
 * @BelongsProject: mhl
 * @BelongsPackage: com.qinbo.mhl.service
 * @Author: 别来无恙qb
 * @CreateTime: 2022-05-23  22:31
 * @Description: 该类完成对employee表的各种操作(通过调用EmployeeDAO对象完成)
 */
public class EmployeeService {
    //定义一个 EmployeeDAO 属性
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    //方法，根据empId 和 pwd 返回一个Employee对象
    //如果查询不到，就返回null
    public Employee getEmployeeByIdAndPwd(String empId, String pwd){
        return employeeDAO.querySingle("select * from employee where empId=? and pwd=md5(?)",Employee.class,empId,pwd );

    }

}
