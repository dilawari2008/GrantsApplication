import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist({
  key: "userState",
});

const userState = atom({
  key: "userState",
  default: {
    firstName: '',
    lastName: '',
    username: '',
    userId: '',
  },
  effects_UNSTABLE: [persistAtom],
});

export default userState;
