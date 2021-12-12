import React from "react";
import {SurveyItemStore} from "./service/SurveyItemStore";
import {SurveyCategoryStore} from "./service/SurveyCategoryStore";

class RootStore {
  constructor() {
    this.surveyItemStore = new SurveyItemStore(this)
    this.surveyCategoryStore = new SurveyCategoryStore(this)
  }
}

export const StoresContext = React.createContext(new RootStore());
export const useStores = () => React.useContext(StoresContext);