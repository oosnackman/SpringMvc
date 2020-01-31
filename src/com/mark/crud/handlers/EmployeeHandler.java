package com.mark.crud.handlers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mark.crud.dao.DepartmentDao;
import com.mark.crud.dao.EmployeeDao;
import com.mark.crud.entities.Employee;

/**
 * Description:
 * 
 * @author MarkLin
 * @Date:2019年11月4日下午3:08:27
 * @Remarks:
 */
@Controller
public class EmployeeHandler {

	@Autowired
	EmployeeDao employeeDao;

	@Autowired
	DepartmentDao departmentDao;
	
	@Autowired
	HttpServletRequest  request;

	/**
	 * Description:
	 * 
	 * @author MarkLin
	 * @Date:2019年11月5日上午11:44:39
	 * @Remarks: 1.會在每個目標方法之前被SpringMVC執行 , 2.在呼叫 update(Employee
	 *           employee)方法時,會先把資料放入Map中，key為employee(注意:key為傳入參數的,類型第一個字母小寫)
	 *           ,SpringMvc又會把上述跟傳入目標參數做為更新。
	 */
	@ModelAttribute
	public void getEmployee(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {

		if (id != null)
			map.put("employee", employeeDao.get(id));
	}

	/**
	 * Description:新增頁面
	 * 
	 * @author MarkLin
	 * @Date:2019年11月5日上午11:56:43
	 * @Remarks:
	 */
	@RequestMapping(value = "emp", method = RequestMethod.GET)
	public String iput(Map<String, Object> map) {

		map.put("departments", departmentDao.getDepartments());
		map.put("employee", new Employee());
		return "iput";

	}

	/**
	 * Description:更新頁面
	 * 
	 * @author MarkLin
	 * @Date:2019年11月5日上午11:55:34
	 * @Remarks:
	 */
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	public String iput(@PathVariable("id") Integer id, Map<String, Object> map) {

		map.put("employee", employeeDao.get(id));
		map.put("departments", departmentDao.getDepartments());
		return "iput";

	}

	/**
	 * Description:新增
	 * 
	 * @author MarkLin
	 * @Date:2019年11月5日上午11:55:11
	 * @Remarks:
	 */
	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public String save(Employee employee, BindingResult mseResult) {

		// Date,Float轉換失敗,的錯誤消息
		if (mseResult.getErrorCount() > 0) {
			for (FieldError msg : mseResult.getFieldErrors()) {
				System.out.println("msg:" + msg.getField() + " , " + msg.getDefaultMessage());
			}
		}
		employeeDao.save(employee);
		return "redirect:/emps";

	}

	/**
	 * Description:更新
	 * 
	 * @author MarkLin
	 * @Date:2019年11月5日上午11:54:55
	 * @Remarks: 1.若在@ModelAttribute("xxxx")目標參數加入此方法,則會與在@ModelAttribute方法內的Map.put(key,value)的
	 *           key相呼對應是否一致 ,否則則使用默認的"類型第一個字母小寫"
	 */
	@RequestMapping(value = "/emp", method = RequestMethod.PUT)
	public String update(@ModelAttribute("employee") Employee employee) {
		employeeDao.save(employee);
		return "redirect:/emps";
	}

	/**
	 * Description:刪除
	 * 
	 * @author MarkLin
	 * @Date:2019年11月5日上午11:55:18
	 * @Remarks:
	 */
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		employeeDao.delete(id);
		return "redirect:/emps";
	}

	/**
	 * Description:列表頁面
	 * 
	 * @author MarkLin
	 * @Date:2019年11月5日上午11:56:24
	 * @Remarks:
	 */
	@RequestMapping("/emps")
	public String list(Map<String, Object> map) {
		map.put("employees", employeeDao.getAll());
		return "list";
	}

	// 以下為單元功能演示----------------------------------------------------------------------------

	/**
	 * Description:
	 * 
	 * @author MarkLin
	 * @Date:2019年11月5日下午3:38:26
	 * @Remarks: 1.需加入
	 *           jackson-annotations-2.1.5.jar,jackson-core-2.1.5.jar,jackson-databind-2.1.5.jar
	 */
	@ResponseBody
	@RequestMapping("/dojson")
	public Collection<Employee> dojson() {
		return employeeDao.getAll();
	}



	/**
	 * Description:可對WebDataBinder初始化
	 * 
	 * @author MarkLin
	 * @Date:2019年11月5日下午1:49:37
	 * @Remarks:
	 */
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

	}


}
