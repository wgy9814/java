package chapter26.src.com.wgy.mhl.domain;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/21 15:48
 */

/**
 * @BelongsProject: mhl
 * @BelongsPackage: com.qinbo.mhl.domain
 * @Author: 别来无恙qb
 * @CreateTime: 2022-05-24  21:35
 * @Description:这是一个javabean 和 diningTable 表对应
 *  *  id int primary key auto_increment, #自增, 表示餐桌编号
 *  *  state varchar(20) not null default '',#餐桌的状态
 *  *  orderName varchar(50) not null default '',#预订人的名字
 *  *  orderTel varchar(20) not null default ''
 */
public class DiningTable {

    private Integer id;
    private String state;
    private String orderName;
    private String orderTel;

    public DiningTable() {
    }

    public DiningTable(Integer id, String state, String orderName, String orderTel) {
        this.id = id;
        this.state = state;
        this.orderName = orderName;
        this.orderTel = orderTel;
    }

    @Override
    public String toString() {
        return id + "\t\t\t" + state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }
}

