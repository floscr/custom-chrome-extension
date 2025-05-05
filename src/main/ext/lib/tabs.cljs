(ns ext.lib.tabs
  (:require
   [promesa.core :as p]
   [ext.lib.chrome :as chrome]))

(defn close-non-selected! []
  (p/let [[active-tab] (chrome/current-window-tabs+ {:active true})
          inactive-tabs (chrome/current-window-tabs+ {:active false
                                                      :pinned false})
          active-group-id (.-groupId active-tab)]
    (doseq [tab inactive-tabs
            :when (= (.-groupId tab) active-group-id)]
      (js/chrome.tabs.remove (.-id tab)))))

(defn pin-selected! []
  (p/let [[tab] (chrome/current-window-tabs+ {:active true})]
    (js/chrome.tabs.update (.-id tab) #js {:pinned (not ^js/bool (.-pinned tab))})))
