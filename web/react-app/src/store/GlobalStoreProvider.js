import {createContext} from "react";
import RootStore from "./RootStore";


export const GlobalStoreContext = createContext(undefined);

const GlobalStoreProvider = ({children}) => {
  return (
    <GlobalStoreContext.Provider value={new RootStore()}>
      {children}
    </GlobalStoreContext.Provider>
  )
}

export default GlobalStoreProvider;