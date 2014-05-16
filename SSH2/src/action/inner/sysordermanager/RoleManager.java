package action.inner.sysordermanager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Power;
import bean.Role;
import bean.RoleType;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import service.PowerService;
import service.RoleService;
import service.RoleTypeService;

@Controller @Scope("prototype")
public class RoleManager extends ActionSupport {
    private static final long serialVersionUID = 1L;

	@Resource
	private RoleService roleService;
	@Resource
	private RoleTypeService roleTypeService;
	@Resource
	private PowerService powerService;

    /**
     * 查询所有角色类别
     * @return
     */
    public String ListRoleCalss(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("json/javascript;charset=utf-8");

		List roleTypes = roleTypeService.getRoleTypes();
		JSONArray json = new JSONArray();

		for (Object o : roleTypes){
			RoleType roleType = (RoleType) o;
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id",roleType.getId().toString());
			map.put("name",roleType.getTypeName());
			json.add(map);
		}
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询某一角色类别的所有角色
     * @return
     */
    public String OneClassRoles(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("json/javascript;charset=utf-8");

        //角色类别ID
        String classId = request.getParameter("classId");
        //角色类别名称
        String className = request.getParameter("className");

		RoleType roleType = roleTypeService.getRoleType(Integer.parseInt(classId));
		Set roles = roleType.getRole();
		JSONArray json = new JSONArray();
		for (Object o : roles){
			Role role = (Role) o;
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id",role.getId().toString());
			map.put("name",role.getRoleName());
			json.add(map);
		}
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 新增一个角色类别
     * @return
     */
    public String AddRoleClass(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("json/javascript;charset=utf-8");

        //新增的角色类别名称
        String className = request.getParameter("className");

		RoleType roleType = new RoleType();
		roleType.setTypeName(className);
		boolean a = roleTypeService.addRoleType(roleType);
		String tag = null;
		String id = null;
		if (a==true){
			tag = "success";
			List list = roleTypeService.getRoleTypeByName(className);
			RoleType type = (RoleType) list.get(0);
			id = type.getId().toString();
		}
		else
			tag = "failed";

		HashMap<String, String> map = new HashMap<String, String>();
        map.put("state", tag);
		map.put("id",id);

        JSONObject json = JSONObject.fromObject(map);
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询全部权限，对当前角色类别已有的权限进行标记
     * @return
     */
    public String SelectAllOrder(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("json/javascript;charset=utf-8");

        //当前角色类别的ID
        String roleClassId = request.getParameter("roleClassId");

		List powers = powerService.getPowers();
		RoleType roleType = roleTypeService.getRoleType(Integer.parseInt(roleClassId));


		HashMap<String, String> map1 = new HashMap<String, String>();
        HashMap<String, String> map2 = new HashMap<String, String>();
        map1.put("orderId", "1");
        map1.put("orderName", "查看");
        map1.put("own", "1");

        map2.put("orderId", "2");
        map2.put("orderName", "打印");
        map2.put("own", "0");

        JSONArray json = new JSONArray();
        json.add(map1);
        json.add(map2);
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存对角色类别的权限更改
     * @return
     */
    public String SaveClassOrderChange(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("json/javascript;charset=utf-8");

        //当前角色类别ID
        String classId = request.getParameter("classId");
        //当前角色类别的权限
        String orderList = request.getParameter("orderList");
		System.out.println(orderList);

        String []l = orderList.split("|");


        HashMap<String, String> map = new HashMap<String, String>();
        map.put("state", "success");
        JSONObject json = JSONObject.fromObject(map);
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除某一个角色类别
     * @return
     */
    public String DelRoleClass(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("json/javascript;charset=utf-8");

        //需要删除的角色类别ID
        String classId = request.getParameter("roleClassId");
		RoleType roleType = roleTypeService.getRoleType(Integer.parseInt(classId));
		boolean a = roleTypeService.deleteRoleType(roleType);
		String tag = "failed";
		if (a==true)
			tag = "success";
		HashMap<String, String> map = new HashMap<String, String>();
        map.put("state", tag);
        JSONObject json = JSONObject.fromObject(map);
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 新增一个系统角色
     * @return
     */
    public String SaveOneSystemRole(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("json/javascript;charset=utf-8");

        //角色类别ID
        String classId = request.getParameter("classId");
        //新增的系统角色名称
        String roleName = request.getParameter("roleName");

		Role role = new Role();
		role.setRoleName(roleName);
		boolean a = roleService.addRole(role);
		String tag = "failed";
		HashMap<String, String> map = new HashMap<String, String>();
		if (a==true){             //添加角色成功
			role = roleService.getRoleByName(roleName);
			RoleType roleType = roleTypeService.getRoleType(Integer.parseInt(classId));
			roleType.addRole(role);
			roleTypeService.updateRoleType(roleType);

			tag = "success";
			map.put("roleId", role.getId().toString());
		}
        map.put("state", tag);

        JSONObject json = JSONObject.fromObject(map);
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 移动角色，将角色从原来的类别移动到新的类别
     * @return
     */
    public String MoveRoleToOtherClass(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("json/javascript;charset=utf-8");

        //角色原来的类别
        String oldClassId = request.getParameter("oldClassId");
        //角色移动到的新类别
        String newClassId = request.getParameter("newClassId");
        //具体角色的ID
        String roleId = request.getParameter("roleId");

		Role role = roleService.getRole(Integer.parseInt(roleId));
		RoleType oldType = roleTypeService.getRoleType(Integer.parseInt(oldClassId));
		RoleType newType = roleTypeService.getRoleType(Integer.parseInt(newClassId));

		String tag = "failed";
		if (role!=null && oldType!=null && newType!=null){
			oldType.deleteRole(role);
			newType.addRole(role);
			roleTypeService.updateRoleType(oldType);
			roleTypeService.updateRoleType(newType);
			tag = "success";
		}
		HashMap<String, String> map = new HashMap<String, String>();
        map.put("state", tag);
        JSONObject json = JSONObject.fromObject(map);
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除一个系统角色
     * @return
     */
    public String DeleteRole(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("json/javascript;charset=utf-8");

        //角色类别
        String classId = request.getParameter("classId");
		String roleId = request.getParameter("roleId");

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("state", "success");
        JSONObject json = JSONObject.fromObject(map);

        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *查询所有权限，对当前系统角色拥有的权限标记
     * @return
     */
    public String SelectRoleOrders(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("json/javascript;charset=utf-8");

        //当前角色
        String roleId = request.getParameter("roleId");

		Role role = roleService.getRole(Integer.parseInt(roleId));

		Set rolePowers = role.getPower();
		List allPowers = powerService.getPowers();

		JSONArray json = new JSONArray();
		for (Object o : allPowers){
			Power power = (Power) o;
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("orderId",power.getId().toString());
			map.put("orderName",power.getPowerName());
			if (rolePowers.contains(power))
				map.put("own","1");
			else
				map.put("own","0");

			json.add(map);
		}

        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存对当前角色的权限更改
     * @return
     */
    public String SaveRoleOrderChange(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("json/javascript;charset=utf-8");

        //当前角色ID
        String roleId = request.getParameter("roleId");
        //当前角色的权限
        String orderList = request.getParameter("orderList");

		String[] split = orderList.split("\\|");
		String tag = "failed";

		Role role = roleService.getRole(Integer.parseInt(roleId));
		if (role != null){
			role.getPower().clear();           //清空权限
			roleService.updateRole(role);
			if (!orderList.equals("")){        //有新的权限加入
				for (String s : split){
					Power power = powerService.getPower(Integer.parseInt(s));
					power.addRole(role);
					powerService.updatePower(power);
				}
			}
			tag = "success";
		}

		HashMap<String, String> map = new HashMap<String, String>();
        map.put("state", tag);
        JSONObject json = JSONObject.fromObject(map);
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}













