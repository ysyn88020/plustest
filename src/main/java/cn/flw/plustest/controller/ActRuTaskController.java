package cn.flw.plustest.controller;

import cn.flw.plustest.utils.BeanJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import cn.flw.plustest.service.IActRuTaskService;
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import cn.flw.plustest.utils.Result;
import cn.flw.plustest.entity.ActRuTask;
/**
* <p>
	* ActRuTask前端控制器
	* </p>
*
* @author f_liwen
* @since 2019-12-21
*/
@RestController
@Api(tags = "ActRuTask")
@RequestMapping("/actRuTask")
public class ActRuTaskController {
@Autowired
public IActRuTaskService actRuTaskService;

	/**
	* 保存单条
	* @param param 保存参数
	* @return 是否添加成功
	*/
	@ApiOperation(value = "保存", notes = "保存数据到ActRuTask")
	@RequestMapping(value = "/add.json", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public ResponseEntity<Result> addActRuTask(@RequestBody String param) {

		// 结果封装类对象
		ActRuTask t=BeanJsonUtil.jstr2object(param,ActRuTask.class);
		Result res = new Result(200, "ok");
		boolean result = actRuTaskService.save(t);
		res.putData("result",result);
		return ResponseEntity.ok(res);
		}

	/**
	* 更新(根据主键id更新)
	* @param param 修改参数
	* @return 是否更改成功
	*/
	@ApiOperation(value = "更新数据", notes = "根据主键id更新ActRuTask数据")
	@RequestMapping(value = "/updateById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public ResponseEntity<Result> updateActRuTaskById(@RequestBody String param) {
		ActRuTask t=BeanJsonUtil.jstr2object(param,ActRuTask.class);
		boolean result = actRuTaskService.updateById(t);
		Result res = new Result(200, "ok");
		res.putData("result",result);
		return ResponseEntity.ok(res);
		}

		/**
		* 删除(根据主键id伪删除)
		* @param param 主键id
		* @return 是否删除成功
		*/
	@ApiOperation(value = "删除数据", notes = "根据主键id伪删除ActRuTask数据")
	@RequestMapping(value = "/deleteById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public ResponseEntity<Result> deleteActRuTaskById(@RequestBody String param) {
		ActRuTask t=BeanJsonUtil.jstr2object(param,ActRuTask.class);
		boolean result = actRuTaskService.removeById(param);
		Result res = new Result(200, "ok");
		res.putData("result",result);
		return ResponseEntity.ok(res);
		}

	/**
	* 根据主键id查询单条
	* @param param 主键id
	* @return 查询结果
	*/
	@ApiOperation(value = "获取单条数据", notes = "根据主键id获取ActRuTask数据")
	@RequestMapping(value = "/getById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public ResponseEntity<Result> getActRuTaskById(@RequestBody String param) {
		ActRuTask t=BeanJsonUtil.jstr2object(param,ActRuTask.class);
		QueryWrapper<ActRuTask > queryWrapper = new QueryWrapper<>(t);
		ActRuTask result = actRuTaskService.getOne(queryWrapper);
		Result res = new Result(200, "ok");
		res.putData("result",result);
		return ResponseEntity.ok(res);
		}

	/**
	* 查询全部
	* @param param 查询条件
	* @return 查询结果
	*/
	@ApiOperation(value = "全部查询", notes = "查询ActRuTask全部数据")
	@RequestMapping(value = "/queryAll", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public ResponseEntity<Result> getActRuTaskAll(@RequestBody String param) {
		ActRuTask t=BeanJsonUtil.jstr2object(param,ActRuTask.class);
		QueryWrapper<ActRuTask> queryWrapper = new QueryWrapper<>(t);
		List<ActRuTask> result = actRuTaskService.list(queryWrapper);
		Result res = new Result(200, "ok");
		res.putData("result",result);
		return ResponseEntity.ok(res);
			}

	/**
	* 分页查询
	* @param param 查询条件
	* @return 查询结果
	*/
	@ApiOperation(value = "分页查询", notes = "分页查询ActRuTask全部数据")
	@RequestMapping(value = "/queryPage.json", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public ResponseEntity<Result> getActRuTaskPage(@RequestBody String param) {
		Map<String,Object> pageMap=BeanJsonUtil.jstr2object(param,Map.class);
		Integer pageNo = Integer.valueOf((Integer) pageMap.get("pageNo"));
		Integer pageSize = Integer.valueOf((Integer) pageMap.get("pageSize"));
		IPage<ActRuTask>page = new Page<>(pageNo, pageSize);
		QueryWrapper<ActRuTask> wrapper = new QueryWrapper<>();
		String actRuTaskStr=  pageMap.get("actRuTaskStr").toString();
		ActRuTask t=BeanJsonUtil.jstr2object(param,ActRuTask.class);
		wrapper.setEntity(t);
		IPage<ActRuTask> result = actRuTaskService.page(page,wrapper);
		Result res = new Result(200, "ok");
		res.putData("result",result);
		return ResponseEntity.ok(res);
			}
}

