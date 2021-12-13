import {action, makeAutoObservable, observable} from "mobx";
import _ from "lodash";
import {SurveyItem} from "./SurveyItem";

export class SurveySubCategory {
  id = ""
  code = ""
  title = ""
  useYn = ""
  reg_dt = null
  edit_dt = null
  del_dt = null
  surveyItems = []

  store = null
  saveHandler = null

  constructor(store, item) {
    makeAutoObservable(this, {
      id: observable,
      surveyItems: observable,
      setSurveyItems: action,
    })
    this.store = store

    this.id = item.id
    this.code = item.code
    this.title = item.title
    this.reg_dt = item.reg_dt
    this.edit_dt = item.edit_dt
    this.del_dt = item.del_dt
    this.setSurveyItems(item);
  }

  setSurveyItems(item) {
    this.surveyItems = []
    if (!_.isNil(item.surveyItemEntities)) {
      item.surveyItemEntities.forEach(surveyItem => {
        this.surveyItems.push(new SurveyItem(this, surveyItem))
      })
    }
  }
}