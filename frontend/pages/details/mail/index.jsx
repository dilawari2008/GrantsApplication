"use client";
import dynamic from "next/dynamic";
const CustomTable = dynamic(
  async () => await import("../../../components/CustomTable"),
  {
    ssr: false,
  }
);
import Navbar from "../../../components/Navbar";
import Spinner from "../../../components/Spinner";
import { useEffect, useState } from "react";
import { useRecoilValue } from "recoil";
import foundationState from "../../../atom/foundationState";
import userState from "@/atom/userState";
import { formatDateTime, truncateText } from "@/utils";
import ApiHelper from "@/helper/ApiHelper";
const CustomModal = dynamic(
  async () => await import("../../../components/CustomModal"),
  {
    ssr: false,
  }
);

const Mail = () => {
  const foundationValue = useRecoilValue(foundationState);
  const userValue = useRecoilValue(userState);
  const [isLoading, setIsLoading] = useState(false);
  const [data, setData] = useState(null);
  const [currentRecord, setCurrentRecord] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const getEmailsList = async (foundationId, pageSize, pageNumber) => {
    setIsLoading(true);
    const emails = await ApiHelper.getEmailsList(
      foundationId,
      pageSize,
      pageNumber
    );
    setData(emails);
    setIsLoading(false);
  };

  useEffect(() => {
    getEmailsList(foundationValue?.foundationId, 5, 1);
  }, []);

  console.log("data", data);
  const columns = [
    {
      title: "JobId",
      dataIndex: "id",
      key: "id",
    },
    // {
    //   title: "Name",
    //   dataIndex: "name",
    //   key: "name",
    // },
    // {
    //   title: "Non Profit Email",
    //   dataIndex: "nonProfitEmail",
    //   key: "nonProfitEmail",
    // },
    // {
    //   title: "Foundation Email",
    //   dataIndex: "foundationEmail",
    //   key: "foundationEmail",
    // },
    {
      title: "Subject",
      dataIndex: "subject",
      key: "subject",
      render: (_, record) => truncateText(record?.subject),
    },
    {
      title: "Body",
      dataIndex: "body",
      key: "body",
      render: (_, record) => truncateText(record?.body),
    },
    {
      title: "Last Updated At",
      dataIndex: "createdAt",
      key: "createdAt",
    },
    {
      title: "View",
      key: "view",
      render: (_, record) => (
        <p
          className="text-blue-500 underline underline-offset-2 hover:cursor-pointer"
          onClick={() => {
            console.log(record);
            setCurrentRecord(record);
            setIsModalOpen(true);
          }}
        >
          View
        </p>
      ),
    },
    {
      title: "Status",
      dataIndex: "mailStatus",
      key: "mailStatus",
      render: (_, record) => (
        <>
          {record?.mailStatus === "SUCCESS" ? (
            <p className="text-green-800 font-semibold">{record?.mailStatus}</p>
          ) : record?.mailStatus === "QUEUED" ? (
            <p className="text-yellow-500 font-semibold">
              {record?.mailStatus}
            </p>
          ) : record?.mailStatus === "FAILED" ? (
            <p className="text-red-600 font-semibold">{record?.mailStatus}</p>
          ) : (
            "-"
          )}
        </>
      ),
    },
  ];
  const tableData = [];
  for (let idx = 0; idx < data?.content?.length; idx++) {
    const item = data?.content?.[idx];
    tableData.push({
      key: idx,
      name: item.name,
      id: item.id,
      nonProfitEmail: item.nonProfitEmail,
      email: item.email,
      foundationEmail: item.foundationEmail,
      subject: item.subject,
      body: item.body,
      mailStatus: item?.mailStatus,
      createdAt: formatDateTime(item.createdAt),
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
                <p className="font-bold  underline">
                  {foundationValue?.foundationName}
                </p>
              </div>

              <div> | </div>

              <div className="flex gap-3  px-4 ">
                <p>User Name</p>
                <p>-</p>
                <p className="font-bold  underline">
                  {userValue?.firstName + " " + userValue?.lastName}
                </p>
              </div>
            </div>

            <CustomTable
              columns={columns}
              data={tableData}
              onPageChange={(pagination, filters, sorter) => {
                console.log(pagination, filters, sorter);
                getEmailsList(foundationValue?.foundationId, pagination?.pageSize, pagination?.current);
              }}
              pageSize={5}
              total={data?.totalElements}
              className="min-h-[410px] min-w-[90vw] mt-20 p-2 bg-zinc-100"
              checkboxDisabled={true}
              currentPage={(data?.number || 0) + 1}
            />

            <CustomModal isOpen={isModalOpen} onCancel={() => onModalClose()}>
              <ModalContent
                body={currentRecord?.body}
                subject={currentRecord?.subject}
                createdAt={currentRecord?.createdAt}
                from={currentRecord?.foundationEmail}
                to={currentRecord?.nonProfitEmail}
                onModalClose={onModalClose}
              />
            </CustomModal>
          </div>
        </div>
      ) : (
        <div className="flex items-center justify-center w-[100vw] h-[100vh] overflow-hidden">
          <Spinner variant="primary" size="large" />
        </div>
      )}
    </>
  );
};

const ModalContent = ({
  body,
  subject,
  createdAt,
  from,
  to,
  onModalClose,
  ...props
}) => {
  return (
    <div className="flex flex-col gap-4">
      <p className="font-semibold text-2xl">{subject}</p>
      <div className="flex gap-3">
        <p>From -</p>
        <p>{from}</p>
      </div>
      <div className="flex gap-3">
        <p>To -</p>
        <p>{to}</p>
      </div>
      <p className="">{createdAt}</p>
      <p className="">{body}</p>
    </div>
  );
};

export default Mail;
