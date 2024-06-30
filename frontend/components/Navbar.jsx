import TemelioWhite from "@/public/temelio-white-text.svg";
import Image from "next/image";
import { useRouter } from "next/router";
import { twMerge } from "tailwind-merge";

const Navbar = () => {
  const router = useRouter();

  return (
    <nav className="w-full h-[10vh] fixed z-40">
      <div className="px-4 md:px-16 py-6 flex items-center transition duration-500 bg-primary bg-opacity-100 justify-between">
        <div>
          <Image
            alt="temelio-logo"
            src={TemelioWhite}
            onClick={() => router.push("/login")}
            className="hover:cursor-pointer"
          />
        </div>

        <div className="flex gap-20 text-white items-center justify-center font text-xl">
          <div
            className={twMerge(router.pathname === "/details/non-profit" ? "underline underline-offset-4" : "", 'hover:cursor-pointer hover:underline hover:underline-offset-4')}
            onClick={() => router.push("/details/non-profit")}
          >
            Non Profits
          </div>
          <div
            className={twMerge(router.pathname === "/details/mail" ? "underline underline-offset-4" : "", 'hover:cursor-pointer hover:underline hover:underline-offset-4')}
            onClick={() => router.push("/details/mail")}
          >
            Emails
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
