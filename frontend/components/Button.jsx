import { twMerge } from "tailwind-merge";
import Spinner from "./Spinner";

const Button = ({ className, isLoading, text, children, ...props }) => {
  return (
    <div className="">
      <button
        className={twMerge(
          "px-4 py-2 bg-cta rounded-md w-full flex justify-center items-center min-h-[40px] duration-500 hover:bg-yellow-400 hover:scale-105 hover:text-stone-700 font-normal outline-none",
          className
        )}
        {...props}
      >
        {isLoading ? <Spinner className="text-white" size="medium" /> : text}
        {children}
      </button>
    </div>
  );
};

export default Button;
