(ns ext.background
  (:require
   [ext.lib.tabs :as tabs]))

;; Chrome Handlers -------------------------------------------------------------

(defn on-command [command]
  (condp = command
    "close-other-tabs" (tabs/close-non-selected!)
    "pin-tab" (tabs/pin-selected!)
    "kill-sticky" (tabs/kill)))

;; Main ------------------------------------------------------------------------

(defn init []
  (js/chrome.commands.onCommand.addListener on-command))
