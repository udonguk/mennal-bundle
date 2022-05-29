import React, {useContext} from "react";
import {StoresContext} from "../../../store/RootStore";
import ResultInfoBasic from "./ResultInfoBasic";
import {observer} from "mobx-react";

const ResultInfosBasic = observer(() => {
  const store = useContext(StoresContext);
  const surveyItemStore = store.surveyItemStore;

  return <>
    {surveyItemStore.surveyResult
      && surveyItemStore.surveyResult.map(item => <ResultInfoBasic item={item}/>)}
  </>
})


export default ResultInfosBasic