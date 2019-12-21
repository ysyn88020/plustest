package cn.flw.plustest.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author f_liwen
 * @since 2019-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ActRuTask对象", description="")
public class ActRuTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID_")
    private String id;

    @TableField("REV_")
    private Integer rev;

    @TableField("EXECUTION_ID_")
    private String executionId;

    @TableField("PROC_INST_ID_")
    private String procInstId;

    @TableField("PROC_DEF_ID_")
    private String procDefId;

    @TableField("NAME_")
    private String name;

    @TableField("PARENT_TASK_ID_")
    private String parentTaskId;

    @TableField("DESCRIPTION_")
    private String description;

    @TableField("TASK_DEF_KEY_")
    private String taskDefKey;

    @TableField("OWNER_")
    private String owner;

    @TableField("ASSIGNEE_")
    private String assignee;

    @TableField("DELEGATION_")
    private String delegation;

    @TableField("PRIORITY_")
    private Integer priority;

    @TableField("CREATE_TIME_")
    private LocalDateTime createTime;

    @TableField("DUE_DATE_")
    private LocalDateTime dueDate;

    @TableField("CATEGORY_")
    private String category;

    @TableField("SUSPENSION_STATE_")
    private Integer suspensionState;

    @TableField("TENANT_ID_")
    private String tenantId;

    @TableField("FORM_KEY_")
    private String formKey;

    @TableField("CLAIM_TIME_")
    private LocalDateTime claimTime;


}
