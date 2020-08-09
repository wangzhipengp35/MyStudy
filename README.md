# MyStudy
### 数据结构

#### 稀疏数组

当一个数组中存在大部分元素为0，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。

稀疏数组的处理方法;
1. 记录数组一共有几行几列，有多少个不同的值。
2. 把具有不同值的元素的行列有值记录在一个小规模的数组中，从而缩小程序的规模

[知乎稀疏数组样例](https://zhuanlan.zhihu.com/p/79058046)


#### 队列
1. 线性队列 : 队列是一种特殊的线性表，特殊之处在于它只允许在表的前端（front）进行删除操作，而在表的后端（rear）进行插入操作，和栈一样，队列是一种操作受限制的线性表。进行插入操作的端称为队尾，进行删除操作的端称为队头。
2. 环形队列 : front 代表元素首位的下标， rear代表末未元素的下标
判断有效元素 ： rear - front


