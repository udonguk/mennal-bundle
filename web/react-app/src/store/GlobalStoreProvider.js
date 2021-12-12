import {createContext} from "react";
import RootStore from "./RootStore";

// unused js
export const GlobalStoreContext = createContext(undefined);

const GlobalStoreProvider = ({children}) => {
  // const store = useLocalStore(new RootStore());
  return (
    <GlobalStoreContext.Provider value={new RootStore()}>
      {children}
    </GlobalStoreContext.Provider>
  )
}

export default GlobalStoreProvider;