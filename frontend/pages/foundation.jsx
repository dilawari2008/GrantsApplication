import Button from "@/components/Button";
import LeftArrow from "@/components/LeftArrow";
import Spinner from "@/components/Spinner";
import { useRouter } from "next/router";
import React, { useState } from "react";

const CreateFoundation = () => {
  const router = useRouter();
  const [isLoading, setLoading] = useState(false);
  const [showPopup, setShowPopup] = useState(false);
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    username: "",
    foundationName: "",
    email: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    setLoading(true);
    e.preventDefault();
    console.log(formData);
    setShowPopup(true);
    setLoading(false);
    setTimeout(() => {
      router.push("/login");
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
          onClick={() => router.push("/login")}
        >
          <LeftArrow className="size-4" />
          <p>Login</p>
        </div>

        <div className="w-full text-center font-bold text-2xl ">
          User Details
        </div>

        {/* user details */}
        <div className="flex flex-col gap-5 my-5">
          <input
            name="firstName"
            type="text"
            value={formData.firstName}
            onChange={handleChange}
            placeholder="First Name"
            className="px-4 py-2 outline-none rounded-lg text-black"
          ></input>
          <input
            name="lastName"
            type="text"
            value={formData.lastName}
            onChange={handleChange}
            placeholder="Last Name"
            className="px-4 py-2 outline-none rounded-lg text-black"
          ></input>
          <input
            name="username"
            type="text"
            value={formData.username}
            onChange={handleChange}
            placeholder="Username"
            className="px-4 py-2 outline-none rounded-lg text-black"
          ></input>
        </div>

        <br />

        <div className="w-full text-center font-bold text-2xl ">
          Foundation Details
        </div>

        {/* foundation details */}
        <div className="flex flex-col gap-5 my-5">
          <input
            name="foundationName"
            type="text"
            value={formData.foundationName}
            onChange={handleChange}
            placeholder="Foundation Name"
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

        <Button className="font-semibold text-black" isLoading={isLoading} text="Create Foundation">
        </Button>
      </form>

      {showPopup && (
        <div className="absolute top-12 right-12 bg-green-600  text-white font-normal text-md py-4 px-8 flex gap-5 rounded-xl"><p> Saved! Redirecting to Login Page...</p><Spinner/></div>
      )}
    </div>
  );
};

export default CreateFoundation;
