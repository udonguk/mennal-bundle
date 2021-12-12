import {makeAutoObservable, runInAction} from "mobx";
import axios from "axios";
import _ from "lodash";
import {SurveyCategory} from "../domain/SurveyCategory";

// import {getSurveyCategories} from "../../dump/surveyCategoryDump";

export class SurveyCategoryStore {
  parent
  surveyCategories = []
  isLoading = true
  result = {
    'score': []
  }

  constructor(parent) {
    console.debug('SurveyCategoryStore constructor')
    makeAutoObservable(this)
    this.parent = parent
    this.loadSurveyCategories()
  }

  loadSurveyCategories() {
    this.isLoading = true;
    runInAction(async () => {
      0 === this.surveyCategories.length && this.getSurveyCategories((res) => {
        this.surveyCategories = [];
        res.data.forEach(item => {
          this.setCategoryFromServer(item);
        })
        this.isLoading = false
      });
    })
  }

  setCategoryFromServer(item) {
    let categoryItem;
    if (!categoryItem) {
      categoryItem = new SurveyCategory(this, item);
      this.surveyCategories.push(categoryItem);
    }
  }

  getSurveyCategories(func) {
    axios.get(`api/survey/category`)
      .then(res => {
        func(res)
      })
  }

  getSurveyCategory(code = ''){
    if(_.isNil(code)){
      return null
    }

    return this.surveyCategories
      .filter(item => code === item.code)[0]
  }
}