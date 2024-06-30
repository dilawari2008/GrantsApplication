import { useCallback, useState } from "react";
import TemelioBlack from "@/public/temelio-black-text.svg";
import TemelioWhite from "@/public/temelio-white-text.svg";
import Image from "next/image";
import Spinner from "../components/Spinner";
import Button from "../components/Button";
import { useRouter } from "next/router";
import ApiHelper from "../helper/ApiHelper";
import { useSetRecoilState } from "recoil";
import foundationState from "@/atom/foundationState";
import userState from "@/atom/userState";

const Login = () => {
  const setFoundationState = useSetRecoilState(foundationState);
  const setUserState = useSetRecoilState(userState);
  const router = useRouter();
  const [username, setUsername] = useState(undefined);
  const [submitLoading, setSubmitLoading] = useState(false);

  const signIn = async () => {
    setSubmitLoading(true);
    const data = await ApiHelper.getFoundationDetails(username);
    console.log("data", data);
    if (data) {
      setFoundationState({
        foundationId: data?.foundationId,
        foundationName: data?.foundationName,
        foundationEmail: data?.foundationEmail,
      });

      setUserState({
        firstName: data?.firstName,
        lastName: data?.lastName,
        username: data?.username,
        userId: data?.userId,
      });
    }

    setSubmitLoading(false);
    router.push("/details/non-profit");
  };

  return (
    <div className="w-[100vw] h-[100vh] flex flex-col items-center justify-center">
      <div className="flex flex-col w-auto h-auto p-10 rounded-lg bg-primary  gap-4">
        <div className="flex h-6 mb-4 justify-center items-center">
          <Image src={TemelioWhite} alt="temelio-logo" />
        </div>

        <div className="flex m-2 gap-2 ">
          <input
            placeholder="Enter username"
            className="w-60 border-2 border-primary py-2 px-4 rounded-md focus:outline-none"
            value={username}
            onChange={(e) => {
              setUsername(e.target.value);
            }}
          ></input>

          <Button
            text="Submit"
            className="w-auto h-full min-w-[84px]"
            isLoading={submitLoading}
            onClick={() => signIn()}
          ></Button>
        </div>

        <div className="flex items-center justify-center m-2 gap-5">
          <div className="w-32 h-[0.5px] bg-white"></div>
          <div className="text-white">OR</div>
          <div className="w-32 h-[0.5px] bg-white"></div>
        </div>

        <Button
          text="Create Foundation"
          isLoading={false}
          onClick={() => router.push("/foundation")}
        />
      </div>
    </div>
  );
};

export default Login;
