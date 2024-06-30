import foundationState from "@/atom/foundationState";
import Button from "@/components/Button";
import LeftArrow from "@/components/LeftArrow";
import Spinner from "@/components/Spinner";
import ApiHelper from "@/helper/ApiHelper";
import { useRouter } from "next/router";
import React, { useState } from "react";
import { useRecoilValue } from "recoil";

const CreateNonProfit = () => {
  const router = useRouter();
  const foundationValue = useRecoilValue(foundationState);
  const [isLoading, setLoading] = useState(false);
  const [showPopup, setShowPopup] = useState(false);
  const [formData, setFormData] = useState({
    name: "",
    address: "",
    email: "",
    foundationId: foundationValue?.foundationId,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    setLoading(true);
    e.preventDefault();
    console.log(formData);
    await ApiHelper.createNonProfit(formData);
    setShowPopup(true);
    setLoading(false);
    setTimeout(() => {
      router.push("/details/non-profit");
    }, 2000);
  };

  return (
    <div className="relative w-[100vw] h-[100vh] flex justify-center items-center">
      <form
        onSubmit={handleSubmit}
        className="flex flex-col min-w-[30rem] min-h-[35rem] bg-primary p-10 text-white rounded-2xl gap-1"
      >
        <div
          className="flex gap-1 text-xs hover:cursor-pointer"
          onClick={() => router.push("/details/non-profit")}
        >
          <LeftArrow className="size-4" />
          <p>Back</p>
        </div>

        <div className="w-full text-center font-bold text-2xl ">
          Register Non Profit
        </div>

        <div className="flex flex-col gap-5 my-5">
          <input
            name="name"
            type="text"
            value={formData.name}
            onChange={handleChange}
            placeholder="Non Profit Name"
            className="px-4 py-2 outline-none rounded-lg text-black"
          ></input>
          <input
            name="address"
            type="text"
            value={formData.address}
            onChange={handleChange}
            placeholder="Address"
            className="px-4 py-2 outline-none rounded-lg text-black"
          ></input>
          <input
            name="email"
            type="text"
            value={formData.email}
            onChange={handleChange}
            placeholder="Email"
            className="px-4 py-2 outline-none rounded-lg text-black"
          ></input>
        </div>

        <br />

        <Button className="font-semibold text-black" isLoading={isLoading} text="Create Non Profit">
        </Button>
      </form>

      {showPopup && (
        <div className="absolute top-12 right-12 bg-green-600  text-white font-normal text-md py-4 px-8 flex gap-5 rounded-xl"><p> Non Profit Saved!</p><Spinner/></div>
      )}
    </div>
  );
};

export default CreateNonProfit;
