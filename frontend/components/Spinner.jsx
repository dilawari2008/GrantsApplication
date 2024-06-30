import { twMerge } from "tailwind-merge";

const Spinner = ({
  className,
  size = "medium",
  variant = "primary",
  ...props
}) => {
  const variantClasses = {
    primary: "text-white",
    secondary: "text-primary",
  };

  const sizeClasses = {
    tiny: "w-4 h-4",
    small: "w-5 h-5",
    medium: "w-6 h-6",
    large: "w-8 h-8",
  };

  return (
    <div
      className={twMerge(
        "flex justify-center items-center",
        sizeClasses?.[size],
        variantClasses?.[variant]
      )}
    >
      <svg
        className={twMerge("animate-spin", className)}
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        {...props}
      >
        <circle
          className="opacity-10"
          cx="12"
          cy="12"
          r="10"
          stroke="currentColor"
          strokeWidth="4"
        ></circle>
        <path
          className="opacity-75"
          fill="currentColor"
          d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"
        ></path>
      </svg>
    </div>
  );
};

export default Spinner;
