import fetcher from "@/lib/fetcher";
import { useRecoilValue } from "recoil";
import useSWR from "swr";
import foundationState from "../atom/foundationState";

const useNonProfits = () => {
  const foundationValue = useRecoilValue(foundationState);
  const { data, error, isLoading } = useSWR(`${process.env.NEXT_PUBLIC_API_HOST}/app/non-profits/${foundationValue?.foundationId}`, fetcher, {
    revalidateIfStale: false,
    revalidateOnFocus: false,
    revalidateOnReconnect: false,
    revalidateOnMount: true,
  });

  return {
    data,
    error,
    isLoading,
  };
};

export default useNonProfits;
