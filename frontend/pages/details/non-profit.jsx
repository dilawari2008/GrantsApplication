"use client";
import dynamic from "next/dynamic";
const CustomTable = dynamic(
  async () => await import("../../components/CustomTable"),
  {
    ssr: false,
  }
);
import Navbar from "../../components/Navbar";
import useNonProfits from "../../hooks/useNonProfits";
import Spinner from "../../components/Spinner";
import { useState } from "react";
import Button from "../../components/Button";
import { useRecoilValue } from "recoil";
import foundationState from "../../atom/foundationState";
import userState from "@/atom/userState";
import { formatDateTime } from "@/utils";
const CustomModal = dynamic(
  async () => await import("../../components/CustomModal"),
  {
    ssr: false,
  }
);

const NonProfit = () => {
  const foundationValue = useRecoilValue(foundationState);
  const userValue = useRecoilValue(userState);
  const { data, error, isLoading } = useNonProfits();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentRecord, setCurrentRecord] = useState(null);
  const [isSendEmailLoading, setIsSendEmailLoading] = useState(false);
  const [isCreateNonProfitLoading, setIsCreateNonProfitLoading] =
    useState(false);
  console.log("data", data);
  console.log("isLoading", isLoading);
  const columns = [
    {
      title: "Name",
      dataIndex: "name",
      key: "name",
    },
    {
      title: "Address",
      dataIndex: "address",
      key: "address",
    },
    {
      title: "Email",
      dataIndex: "email",
      key: "email",
    },
    {
      title: "Created At",
      dataIndex: "createdAt",
      key: "createdAt",
    },
    {
      title: "Template",
      key: "template",
      render: (_, record) => (
        <p
          className="text-blue-500 underline underline-offset-2 hover:cursor-pointer"
          onClick={() => {
            console.log(record);
            setCurrentRecord(record);
            setIsModalOpen(true);
          }}
        >
          Edit
        </p>
      ),
    },
  ];
  const tableData = [];
  for (let idx = 0; idx < data?.length; idx++) {
    const item = data[idx];
    tableData.push({
      key: idx,
      name: item.name,
      address: item.address,
      email: item.email,
      createdAt: formatDateTime(item.createdAt),
      templateText: item?.template,
    });
  }

  const onModalClose = () => {
    setIsModalOpen(false);
    setCurrentRecord(null);
  };
  return (
    <>
      {!isLoading ? (
        <div className=" flex flex-col h-screen">
          <Navbar />

          <div className="relative flex flex-col items-center justify-center mt-[10vh] w-full h-full overflow-hidden">
            <div className="absolute top-[10vh] left-[5vw] flex items-center justify-around w-[80vw]">
              <div className="flex gap-3  px-4 ">
                <p>Foundation Name</p>
                <p>-</p>
                <p className="font-bold text-primary underline">
                  {foundationValue?.foundationName}
                </p>
              </div>

              <div> | </div>

              <div className="flex gap-3  px-4 ">
                <p>User Name</p>
                <p>-</p>
                <p className="font-bold text-primary underline">{userValue?.userName}</p>
              </div>

              <div> | </div>

              <div className="flex gap-3 px-4 ">
                <Button className="px-10" isLoading={isCreateNonProfitLoading}>
                  Add Non Profit
                </Button>
              </div>

              <div> | </div>

              <div className="flex gap-3 px-4 ">
                <Button className="px-10" isLoading={isSendEmailLoading}>
                  Send Emails
                </Button>
              </div>
            </div>

            <CustomTable
              columns={columns}
              data={tableData}
              onPageChange={(pagination, filters, sorter) =>
                console.log(pagination, filters, sorter)
              }
              pageSize={5}
              total={data?.length}
              className="min-h-[410px] min-w-[90vw] mt-20 p-2 bg-zinc-100"
            />

            <CustomModal
              heading={`Edit Template: ${currentRecord?.name}`}
              isOpen={isModalOpen}
              onCancel={() => onModalClose()}
            >
              <ModalContent
                templateText={data?.template}
                onModalClose={onModalClose}
              />
            </CustomModal>
          </div>
        </div>
      ) : (
        <div className="flex items-center justify-center w-[100vw] h-[100vh] overflow-hidden">
          <Spinner variant="secondary" size="large" />
        </div>
      )}
    </>
  );
};

const ModalContent = ({ templateText, onModalClose, ...props }) => {
  const [template, setTemplate] = useState(templateText);
  const [isLoading, setIsLoading] = useState(false);
  const saveTemplate = () => {
    setIsLoading(true);
    console.log("saveTemplate called", template);
    //api
    onModalClose();
    setIsLoading(false);
  };
  return (
    <div className="flex flex-col gap-4">
      <p>
        Variables available are <span className="font-bold">name</span>,{" "}
        <span className="font-bold">address</span>,{" "}
        <span className="font-bold">email</span> and{" "}
        <span className="font-bold">foundation_name</span>.
      </p>
      <textarea
        placeholder="Create email template"
        className="block w-full px-3 py-2 text-sm border border-gray-300 rounded-md resize-none h-20 overflow-y-auto"
        value={template}
        onChange={(e) => {
          setTemplate(e.target.value);
        }}
        {...props}
      ></textarea>
      <Button
        className=""
        isLoading={isLoading}
        text="Save Template"
        onClick={() => saveTemplate()}
      ></Button>
    </div>
  );
};

export default NonProfit;
