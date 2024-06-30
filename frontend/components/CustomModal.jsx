import React, { useState } from "react";
import { Button, Modal } from "antd";

const CustomModal = ({ heading, description, isOpen, onCancel, children, className, ...props }) => {
  return (
    <>
      <Modal
        title={heading}
        open={isOpen}
        onCancel={() => onCancel()}
        footer={null}
        className={className}
      >
        {children}
      </Modal>
    </>
  );
};
export default CustomModal;
