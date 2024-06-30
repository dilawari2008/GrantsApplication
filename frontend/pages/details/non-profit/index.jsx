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
import Button from "../../../components/Button";
import { useRecoilValue } from "recoil";
import foundationState from "../../../atom/foundationState";
import userState from "@/atom/userState";
import { formatDateTime } from "@/utils";
import ApiHelper from "@/helper/ApiHelper";
import { useRouter } from "next/router";
import { SlRefresh } from "react-icons/sl";
const CustomModal = dynamic(
  async () => await import("../../../components/CustomModal"),
  {
    ssr: false,
  }
);

const NonProfit = () => {
  const router = useRouter();
  const foundationValue = useRecoilValue(foundationState);
  const [sendEmailText, setSendEmailText] = useState("Send Email");
  const userValue = useRecoilValue(userState);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [data, setData] = useState(null);
  const [currentRecord, setCurrentRecord] = useState(null);
  const [selectedNonProfitIds, setSelectedNonProfitIds] = useState([]);
  const [isSendEmailLoading, setIsSendEmailLoading] = useState(false);
  const [isCreateNonProfitLoading, setIsCreateNonProfitLoading] =
    useState(false);

  const getNonProfitsList = async (pageSize, pageNumber) => {
    setIsLoading(true);
    const emails = await ApiHelper.getNonProfitsList(
      foundationValue?.foundationId,
      pageSize,
      pageNumber
    );
    setData(emails);
    setIsLoading(false);
  };

  useEffect(() => {
    getNonProfitsList(5, 1);
  }, []);

  console.log("data", data);
  console.log("isLoading", isLoading);
  const columns = [
    {
      title: "Name",
      dataIndex: "name",
      key: "name",
      render: (_, record) => (
        <p
          className=" hover:cursor-pointer"
          onClick={() => {
            router.push(`/details/mail/${record?.id}`);
            // Add any other logic you want to execute on name click here
          }}
        >
          {record.name}
        </p>
      ),
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
  for (let idx = 0; idx < data?.content?.length; idx++) {
    const item = data?.content?.[idx];
    tableData.push({
      id: item.id,
      key: item.id,
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

  const sendEmails = async () => {
    setIsSendEmailLoading(true);
    console.log('selectedNonProfitIds',selectedNonProfitIds)
    await ApiHelper.sendEmails({
      foundationId: foundationValue?.foundationId,
      nonprofitIds: selectedNonProfitIds,
    });
    setIsSendEmailLoading(false);
    setSendEmailText("Sent!");

    setTimeout(() => {
      setSendEmailText("Send Email");
    }, 2000);
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
                <p className="font-bold  underline underline-offset-2">
                  {foundationValue?.foundationName}
                </p>
              </div>

              <div> | </div>

              <div className="flex gap-3  px-4 ">
                <p>User Name</p>
                <p>-</p>
                <p className="font-bold  underline underline-offset-2">
                  {userValue?.firstName + " " + userValue?.lastName}
                </p>
              </div>

              <div> | </div>

              <div className="flex gap-3 px-4 min-w-[170px]">
                <Button
                  className="px-4"
                  isLoading={isCreateNonProfitLoading}
                  onClick={() => router.push("/details/non-profit/create")}
                >
                  Add Non Profit
                </Button>
              </div>

              <div> | </div>

              <div className="flex gap-3 px-4 ">
                <Button className="px-10 min-w-[170px]" isLoading={isSendEmailLoading} onClick={() => sendEmails()} text={sendEmailText}>
                  
                </Button>
              </div>

              <div> | </div>

              <div className="flex gap-3 px-4 hover:cursor-pointer" onClick={() => getNonProfitsList(5,1)}>
                <SlRefresh/>
              </div>
            </div>

            <CustomTable
              columns={columns}
              data={tableData}
              setSelectedNonProfitIds={setSelectedNonProfitIds}
              selectedNonProfitIds={selectedNonProfitIds}
              onPageChange={(pagination, filters, sorter) => {
                console.log(pagination, filters, sorter),
                  getNonProfitsList(
                    pagination?.pageSize,
                    pagination?.current
                  );
              }}
              pageSize={5}
              total={data?.totalElements}
              currentPage={(data?.number || 0) + 1}
              className="min-h-[410px] min-w-[90vw] mt-20 p-2 bg-zinc-100"
            />

            <CustomModal
              heading={`Edit Template: ${currentRecord?.name}`}
              isOpen={isModalOpen}
              onCancel={() => onModalClose()}
            >
              <ModalContent
                templateText={currentRecord?.templateText}
                onModalClose={onModalClose}
                foundationId={foundationValue?.foundationId}
                nonProfitId={currentRecord?.id}
                getNonProfitsList={getNonProfitsList}
                x={console.log('currentRecord',currentRecord)}
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
  templateText,
  onModalClose,
  updateTemplate,
  getNonProfitsList,
  foundationId,
  nonProfitId,
  ...props
}) => {
  console.log("saveTemplate called", templateText);
  const [template, setTemplate] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const saveTemplate = async () => {
    setIsLoading(true);
    console.log("saveTemplate called", template);
    await ApiHelper.updateTemplate({
      foundationId,
      nonProfitId,
      template,
    });
    setTemplate(null);
    onModalClose();
    setIsLoading(false);
    getNonProfitsList(5, 1);
  };

  useEffect(() => {
    setTemplate(templateText);
    console.log('template',template);
    return () => {
      console.log("ModalContent component unmounted");
      setTemplate(null);
    };
  }, [templateText]);
  
  
  
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
        value={template || ''}
        onChange={(e) => {
          setTemplate(e.target.value);
        }}
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
