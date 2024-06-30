import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist({
  key: "foundationState",
});

const foundationState = atom({
  key: "foundationState",
  default: {
    foundationId: '',
    foundationName: '',
    foundationEmail: '',
  },
  effects_UNSTABLE: [persistAtom],
});

export default foundationState;



