import {SurveyItemStore} from "./service/SurveyItemStore";

export default class RootStore {
  surveyItemStore
  constructor() {
    console.debug('RootStore cons')
    this.surveyItemStore = new SurveyItemStore(this)
  }

  get stores(){
    return {...this.surveyItemStore}
  }
}