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
  currentPage,
  setSelectedNonProfitIds,
  selectedNonProfitIds,
}) => {
  const [current, setCurrent] = useState(currentPage || 1);
  const onSelectChange = (newSelectedRowKeys) => {
    console.log("selectedNonProfitIds changed: ", newSelectedRowKeys);
    setSelectedNonProfitIds(newSelectedRowKeys);
  };
  const rowSelection = {
    selectedNonProfitIds,
    onChange: onSelectChange,
  };

  const handleTableChange = (pagination) => {
    setCurrent(pagination?.current);
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
