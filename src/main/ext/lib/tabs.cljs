(ns ext.lib.tabs
  (:require
   [promesa.core :as p]))

(defn close-non-selected! []
  (p/let [current-window (js/chrome.windows.getCurrent)
          tabs (js/chrome.tabs.query #js {:active false
                                          :pinned false
                                          :windowId (.-id current-window)})]
    (doseq [t tabs]
      (js/chrome.tabs.remove (.-id t)))))

(defn pin-selected! []
  (p/let [current-window (js/chrome.windows.getCurrent)
          [tab] (js/chrome.tabs.query #js {:active true
                                           :windowId (.-id current-window)})]
    (js/chrome.tabs.update (.-id tab) #js {:pinned (not ^js/bool (.-pinned tab))})))
