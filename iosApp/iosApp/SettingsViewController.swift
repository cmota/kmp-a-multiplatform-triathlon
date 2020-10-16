//
//  SettingsViewController.swift
//  iosAppx
//

import UIKit
import shared

class SettingsViewController: UIViewController {    
    
    @IBOutlet weak var toggleOnline: UISwitch!
    
    lazy var settingOnline = PlatformSettings.init().settingsRepository.shouldShowOnlyOnlineConferences()

    override func viewDidLoad() {
        super.viewDidLoad()
    
        let online = PlatformSettings.init().settingsRepository.shouldShowOnlyOnlineConferences()
        toggleOnline.setOn(online, animated: true)
        toggleOnline.addTarget(self, action: #selector(onSwitchValueChanged), for: .touchUpInside)
    }
    
    @objc func onSwitchValueChanged(_ toggleOnline: UISwitch) {
        Gutenberg().d(tag: "onSwitchValueChanged", message:"\(toggleOnline.isOn)")
        PlatformSettings.init().settingsRepository.onlyOnlineConferences(state: toggleOnline.isOn)
    }
    
}
