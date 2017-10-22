package org.hadoop.hbase;

//http://hbase.apache.org/book.html#datamodel
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;


/*hbase所谓的三维有序存储的三维是指：rowkey（行主键），column key(columnFamily+qualifier)，timestamp(时间戳)三部分组成的三维有序存储。
1.rowkey，我们知道rowkey是行的主键，而且hbase只能用个rowkey，或者一个rowkey范围即scan来查找数据。所以 rowkey的设计是至关重要的，关系到你应用层的查询效率。
我们知道，rowkey是以字典顺序排序的。而存储的字节码，字典排序
2.column keycolumn key是第二维，数据按rowkey字典排序后，如果rowkey相同，则是根据column key来排序的，也是按字典排序
3.timestamptimestamp 时间戳，是第三维，这是个按降序排序的，即最新的数据排在最前面
*/

//http://www.cnblogs.com/simple-focus/p/6198329.html


public class App 
{
	
	/**
	 * Admin    操作在Tables上 used to create, drop, list, enable and disable tables, add and drop table
     *               column families and other administrative operations
	 * 
	 * Table    单个Table上 Table can be used to get, put, delete or scan data from a table
	 */
    public static void main( String[] args )throws Exception
    {
        Configuration config = new Configuration(false);
        config.set(HConstants.ZOOKEEPER_QUORUM, "n3,n4,n5,n6,n7");
        //config.set(HConstants.ZOOKEEPER_CLIENT_PORT, value);
     
     
        
        try (   Connection connection = ConnectionFactory.createConnection(config);){
        	
        	  Admin admin =  connection.getAdmin();
              if(!admin.tableExists( TableName.valueOf("users"))){
              	HTableDescriptor table = new HTableDescriptor(TableName.valueOf("users"));
              	HColumnDescriptor hColum = new  HColumnDescriptor("user");
              	table.addFamily(hColum);
              	admin.createTable(table);
              }else{
            	  Table table =  connection.getTable(TableName.valueOf("users"));
            	  //Row Key
            	  Put  row = new Put("09403".getBytes());
            	  //列族:限定符(限定符任意不同行之间)
            	  row.addColumn("user".getBytes(), "userName".getBytes(), "xiaoming".getBytes());
            	  row.addColumn("user".getBytes(), "age".getBytes(), NumberUtils.int2byte(60));
            	  table.put(row);
            	 
              }
          // Use the table as needed, for a single operation and a single thread
        } finally {
        //  table.close();
        }
        
        
        
        
    }
    
    
    
    
    
   /* 数据模型概括：

    表（table）---------hbase用表来组织数据。表名是字符串（string）,由可以在文件系统路径里使用的字符组成。

    行（row）---------在表里，数据按行存储。行由行健（rowkey）唯一标识。行健没有数据类型，总是视为字节数组byte[].

    列族（column family）-----------行里的数据按照列族分组，列族也影响到hbase数据的物理存放。因此，它们必须事前定义并且不轻易修改。表中每行拥有相同列族，尽管行不需要在每个列族里存储数据。列族名字是字符串，由可以在文件系统路径里使用的字符组成。(HBase建表是可以添加列族，alter 't1', {NAME => 'f1', VERSIONS => 5} 把表disable后alter,然后enable)

    列限定符（column qualifier）--------列族里的数据通过列限定符或列来定位。列限定符不必事前定义。列限定符不必在不同行之间保持一致，就像行健一样，列限定符没有数据类型，总是视为字节数组byte[].

    单元（cell）-------行健，列族和列限定符一起确定一个单元。存储在单元里的数据称为单元值（value），值也没有数据类型，总是视为字节数组byte[].

    时间版本（version）--------单元值有时间版本，时间版本用时间戳标识，是一个long。没有指定时间版本时，当前时间戳作为操作的基本。hbase保留单元值时间版本的数量基于列族进行配置。默认数量是3个。

       hbase在表里存储数据使用的是四维坐标系统，依次是：行健，列族，列限定符和时间版本。 hbase按照时间戳降序排列各时间版本，其他映射建按照升序排序。*/
}
