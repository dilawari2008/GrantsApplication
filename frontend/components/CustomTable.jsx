import React, { useState } from "react";
import { Table } from "antd";
import { twMerge } from "tailwind-merge";

const CustomTable = ({
  columns,
  data,
  onPageChange,
  pageSize,
  total,
  className,
  checkboxDisabled,
}) => {
  const [selectedRowKeys, setSelectedRowKeys] = useState([]);
  const [current, setCurrent] = useState(1);
  const onSelectChange = (newSelectedRowKeys) => {
    console.log("selectedRowKeys changed: ", newSelectedRowKeys);
    setSelectedRowKeys(newSelectedRowKeys);
  };
  const rowSelection = {
    selectedRowKeys,
    onChange: onSelectChange,
  };

  const handleTableChange = (pagination) => {
    setCurrent(pagination.current);
    onPageChange(pagination);
  };

  return (
    <>
      {checkboxDisabled ? (
        <Table
          columns={columns}
          dataSource={data}
          onChange={handleTableChange}
          pagination={{
            current: current,
            pageSize: pageSize,
            total: total,
          }}
          className={twMerge("", className)}
        />
      ) : (
        <Table
          rowSelection={rowSelection}
          columns={columns}
          dataSource={data}
          onChange={handleTableChange}
          pagination={{
            current: current,
            pageSize: pageSize,
            total: total,
          }}
          className={twMerge("", className)}
        />
      )}
    </>
  );
};

export default CustomTable;
