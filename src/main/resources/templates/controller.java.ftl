package ${package.Controller};

import cn.flw.plustest.utils.BeanJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

#if(${restControllerStyle})
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
#else
import org.springframework.stereotype.Controller;
#end
#if(${superControllerClassPackage})
import ${superControllerClassPackage};
#end
import ${package.Service}.${table.serviceName};
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import cn.flw.plustest.utils.Result;
import ${package.Entity}.${entity};
/**
* <p>
	* ${entity}前端控制器
	* </p>
*
* @author ${author}
* @since ${date}
*/
#if(${restControllerStyle})
@RestController
#else
@Controller
#end
@Api(tags = "${entity}")
@RequestMapping("#if(${package.ModuleName})/${package.ModuleName}#end/#if(${controllerMappingHyphenStyle})${controllerMappingHyphen}#else${table.entityPath}#end")
#if(${kotlin})
class ${table.controllerName}#if(${superControllerClass}) : ${superControllerClass}()#end

#else
#if(${superControllerClass})
public class ${table.controllerName} extends ${superControllerClass} {
#else
public class ${table.controllerName} {
#end
@Autowired
public ${table.serviceName} ${table.entityPath}Service;

	/**
	* 保存单条
	* @param param 保存参数
	* @return 是否添加成功
	*/
	@ApiOperation(value = "保存", notes = "保存数据到${entity}")
	@RequestMapping(value = "/add.json", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public ResponseEntity<Result> add${entity}(@RequestBody String param) {

		// 结果封装类对象
		${entity} t=BeanJsonUtil.jstr2object(param,${entity}.class);
		Result res = new Result(200, "ok");
		boolean result = ${table.entityPath}Service.save(t);
		res.putData("result",result);
		return ResponseEntity.ok(res);
		}

	/**
	* 更新(根据主键id更新)
	* @param param 修改参数
	* @return 是否更改成功
	*/
	@ApiOperation(value = "更新数据", notes = "根据主键id更新${entity}数据")
	@RequestMapping(value = "/updateById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public ResponseEntity<Result> update${entity}ById(@RequestBody String param) {
		${entity} t=BeanJsonUtil.jstr2object(param,${entity}.class);
		boolean result = ${table.entityPath}Service.updateById(t);
		Result res = new Result(200, "ok");
		res.putData("result",result);
		return ResponseEntity.ok(res);
		}

		/**
		* 删除(根据主键id伪删除)
		* @param param 主键id
		* @return 是否删除成功
		*/
	@ApiOperation(value = "删除数据", notes = "根据主键id伪删除${entity}数据")
	@RequestMapping(value = "/deleteById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public ResponseEntity<Result> delete${entity}ById(@RequestBody String param) {
		${entity} t=BeanJsonUtil.jstr2object(param,${entity}.class);
		boolean result = ${table.entityPath}Service.removeById(param);
		Result res = new Result(200, "ok");
		res.putData("result",result);
		return ResponseEntity.ok(res);
		}

	/**
	* 根据主键id查询单条
	* @param param 主键id
	* @return 查询结果
	*/
	@ApiOperation(value = "获取单条数据", notes = "根据主键id获取${entity}数据")
	@RequestMapping(value = "/getById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public ResponseEntity<Result> get${entity}ById(@RequestBody String param) {
		${entity} t=BeanJsonUtil.jstr2object(param,${entity}.class);
		QueryWrapper<${entity} > queryWrapper = new QueryWrapper<>(t);
		${entity} result = ${table.entityPath}Service.getOne(queryWrapper);
		Result res = new Result(200, "ok");
		res.putData("result",result);
		return ResponseEntity.ok(res);
		}

	/**
	* 查询全部
	* @param param 查询条件
	* @return 查询结果
	*/
	@ApiOperation(value = "全部查询", notes = "查询${entity}全部数据")
	@RequestMapping(value = "/queryAll", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public ResponseEntity<Result> get${entity}All(@RequestBody String param) {
		${entity} t=BeanJsonUtil.jstr2object(param,${entity}.class);
		QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>(t);
		List<${entity}> result = ${table.entityPath}Service.list(queryWrapper);
		Result res = new Result(200, "ok");
		res.putData("result",result);
		return ResponseEntity.ok(res);
			}

	/**
	* 分页查询
	* @param param 查询条件
	* @return 查询结果
	*/
	@ApiOperation(value = "分页查询", notes = "分页查询${entity}全部数据")
	@RequestMapping(value = "/queryPage.json", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public ResponseEntity<Result> get${entity}Page(@RequestBody String param) {
		Map<String,Object> pageMap=BeanJsonUtil.jstr2object(param,Map.class);
		Integer pageNo = Integer.valueOf((Integer) pageMap.get("pageNo"));
		Integer pageSize = Integer.valueOf((Integer) pageMap.get("pageSize"));
		IPage<${entity}>page = new Page<>(pageNo, pageSize);
		QueryWrapper<${entity}> wrapper = new QueryWrapper<>();
		String actRuTaskStr=  pageMap.get("actRuTaskStr").toString();
		${entity} t=BeanJsonUtil.jstr2object(param,${entity}.class);
		wrapper.setEntity(t);
		IPage<${entity}> result = ${table.entityPath}Service.page(page,wrapper);
		Result res = new Result(200, "ok");
		res.putData("result",result);
		return ResponseEntity.ok(res);
			}
}

#end